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
    """
    Function to remove all ANSI escape codes from text.
    """
    ansi_escape = re.compile(r'\x1B(?:[@-Z\\-_]|\[[0-?]*[ -/]*[@-~])')
    return ansi_escape.sub('', text)

def clean_response_text(text):
    """
    Function to remove unwanted characters like '━' and '┃' from the response text.
    """
    text = re.sub(r'[━┛┗┛┃]', '', text)
    text = re.sub(r'\n+', '\n', text).strip()
    return text

# Route to serve the frontend
@app.route('/')
def home():
    return render_template('index.html')

# API endpoint for the diet recommendation
@app.route('/diet-recommendation', methods=['POST'])
def diet_recommendation():
    # JSON request from the frontend
    input_data = request.get_json()
    height = input_data.get("height", "Unknown")
    weight = input_data.get("weight", "Unknown")
    age = input_data.get("age", "Unknown")
    goal = input_data.get("goal", "maintain")
    diet_type = input_data.get("diet_type", "veg")
    activity_level = input_data.get("activity_level", "moderate")

    # Constructing a detailed prompt based on user input
    prompt = f"Provide a {diet_type} diet plan for a {age}-year-old person, {height} cm tall, weighing {weight} kg, with a {activity_level} activity level, aiming for {goal}under 200 words."

    # Capture console output
    old_stdout = sys.stdout  # Save the current stdout
    sys.stdout = io.StringIO()  # Redirect stdout to capture output

    try:
        agent.print_response(prompt)  # Generate response using the agent
        response = sys.stdout.getvalue().strip()  # Capture the printed output
    finally:
        sys.stdout = old_stdout  # Restore original stdout

    clean_response = strip_ansi_codes(response)
    clean_response = clean_response_text(clean_response)  # Clean the response
    return jsonify({"response": clean_response.strip()})

if __name__ == '__main__':
    app.run(debug=True,port=5002)
