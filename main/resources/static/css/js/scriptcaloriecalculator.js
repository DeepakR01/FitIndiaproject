// script.js
function calculateCalories() {
    const age = parseInt(document.getElementById("age").value);
    const height = parseInt(document.getElementById("height").value);
    const weight = parseInt(document.getElementById("weight").value);
    const activityLevel = document.getElementById("activityLevel").value;
    const caloriesDiv = document.getElementById("calories");
    const resultDiv = document.getElementById("result");

    // BMR Calculation (Mifflin-St Jeor equation - more accurate)
    const bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;

    // Activity Multiplier
    let activityMultiplier;
    switch (activityLevel) {
        case "sedentary":
            activityMultiplier = 1.2;
            break;
        case "lightlyActive":
            activityMultiplier = 1.375;
            break;
        case "moderatelyActive":
            activityMultiplier = 1.55;
            break;
        case "veryActive":
            activityMultiplier = 1.725;
            break;
        case "extraActive":
            activityMultiplier = 1.9;
            break;
    }

    const maintenanceCalories = Math.round(bmr * activityMultiplier);
    const loseWeightCalories = Math.round(maintenanceCalories * 0.8); // 20% deficit
    const gainWeightCalories = Math.round(maintenanceCalories * 1.2); // 20% surplus

    caloriesDiv.innerHTML = `
        <p><strong>Maintain Weight:</strong> ${maintenanceCalories} calories</p>
        <p><strong>Lose Weight:</strong> ${loseWeightCalories} calories</p>
        <p><strong>Gain Weight:</strong> ${gainWeightCalories} calories</p>
    `;

    resultDiv.classList.remove("hidden");
}