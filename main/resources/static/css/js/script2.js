// script.js
function generateDietPlan() {
    const dietType = document.getElementById("dietType").value;
    const goal = document.getElementById("goal").value;
    const dietPlanDiv = document.getElementById("dietPlan");
    const resultDiv = document.getElementById("result");

    // Sample Diet Plans (Replace with your actual data)
    const dietPlans = {
        vegetarian: {
            lose: "Sample Vegetarian Diet Plan (Lose Weight)",
            gain: "Sample Vegetarian Diet Plan (Gain Weight)",
            maintain: "Sample Vegetarian Diet Plan (Maintain Weight)"
        },
        vegan: {
            lose: "Sample Vegan Diet Plan (Lose Weight)",
            gain: "Sample Vegan Diet Plan (Gain Weight)",
            maintain: "Sample Vegan Diet Plan (Maintain Weight)"
        },
        non_vegetarian: {
            lose: "Sample Non-Vegetarian Diet Plan (Lose Weight)",
            gain: "Sample Non-Vegetarian Diet Plan (Gain Weight)",
            maintain: "Sample Non-Vegetarian Diet Plan (Maintain Weight)"
        }
    };

    const plan = dietPlans[dietType][goal];
    dietPlanDiv.textContent = plan; // Set the plan text
    resultDiv.classList.remove("hidden"); // Show the result div
}