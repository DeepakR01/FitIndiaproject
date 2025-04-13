from flask import Flask, request, jsonify, render_template
from phi.agent import Agent
from phi.model.groq import Groq
from dotenv import load_dotenv
import re
import io
import sys

# Load environment variables
load_dotenv()

# Flask app initialization
app = Flask(__name__,template_folder='templates')

# Initialize the Agent with Groq model
agent = Agent(model=Groq(id="llama-3.3-70b-versatile"))

def strip_ansi_codes(text):
    """Remove ANSI escape codes from text."""
    ansi_escape = re.compile(r'\x1B(?:[@-Z\\-_]|\[[0-?]*[ -/]*[@-~])')
    return ansi_escape.sub('', text)

def clean_response_text(text):
    """Remove metadata and unwanted symbols from the response."""
    text = re.sub(r'┏.*?┓', '', text, flags=re.DOTALL)  # Remove metadata
    text = re.sub(r'[━┛┗┛┃]', '', text)  # Remove unwanted symbols
    text = re.sub(r'\s+', ' ', text).strip()  # Remove extra spaces
    return text.strip()

def is_diet_or_exercise_related(message):
    """Check if the message is related to diet or exercise."""
    keywords = [
        "diet", "meal", "nutrition", "calories", "protein", "carbs", "fat", "fiber", 
        "vitamins", "minerals", "water", "healthy", "weight loss", "weight gain", 
        "muscle", "workout", "exercise", "fitness", "gym", "strength", "cardio", 
        "yoga", "flexibility", "running", "jogging"
    ]
    return any(keyword in message.lower() for keyword in keywords)

def format_response_as_points(response):
    """Convert response into bullet points & numbered list with proper formatting."""
    sentences = response.split(". ")  # Split sentences
    summarized_sentences = sentences[:5]  # Limit to 5 key points

    formatted_points = []
    for sentence in summarized_sentences:
        sentence = sentence.strip()
        if sentence and re.match(r'^\d+', sentence):  
            formatted_points.append(f"\n{sentence}")  # Ensure numbered points start on a new line
        else:
            formatted_points.append(f"- {sentence}")  # Use bullet points for regular points

    return "\n".join(formatted_points).strip()

@app.route('/')
def home():
    return render_template('chat2.html')

@app.route('/chat', methods=['POST'])
def chat():
    input_data = request.get_json()
    user_message = input_data.get("message", "").strip()

    # Filter unrelated questions
    if not is_diet_or_exercise_related(user_message):
        return jsonify({"response": "Sorry, I can't assist with that."})

    # Capture chatbot response
    old_stdout = sys.stdout
    sys.stdout = io.StringIO()

    try:
        agent.print_response(user_message)
        response = sys.stdout.getvalue().strip()
    finally:
        sys.stdout = old_stdout

    clean_response = strip_ansi_codes(response)
    clean_response = clean_response_text(clean_response)
    formatted_response = format_response_as_points(clean_response)  # Proper bullet & numbered points

    return jsonify({"response": formatted_response})

if __name__ == '__main__':
    app.run(debug=True,port=5001)
