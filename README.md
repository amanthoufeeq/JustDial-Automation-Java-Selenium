# 🚗 JustDial Automation Framework

This project is a comprehensive automation framework built using **Java**, **Selenium WebDriver**, **Cucumber (BDD)**, **and TestNG Framework **, to simulate and validate a user journey on the JustDial website. It demonstrates real-world automation scenarios such as service discovery, form validation, menu navigation, and cross-browser testing.

---

## 🎯 Problem Statement

**Objective**: Automate the identification and interaction with car wash services and other features on JustDial.

### Key Requirements:
1. **Identify Car Wash Services**:
   - Display 5 car wash services near the user's location.
   - Services must have:
     - Rating > 4
     - Customer votes > 20
   - Extract and display service name and phone number.

2. **Free Listing Form Validation**:
   - Attempt to register a business with an invalid mobile number.
   - Capture and display the error message.
   - Take a screenshot of the failed attempt.
   - Retry with a valid number and capture a success screenshot.

3. **Menu Navigation and Extraction**:
   - Navigate to Fitness > Gym.
   - Extract all sub-menu items.
   - Store and display them in a list.

---

## 🧪 Test Scenarios

### ✅ Scenario: Extraction of Car Wash Details
- Launching browsers - Cross browser testing
- Handle popups on JustDial homepage
- Search for car wash services
- Apply filters for rating and votes
- Print top-rated services with contact details
- Close browser

### 🧾 Scenario (Cucumber)
```gherkin
	Scenario: Car Wash details extraction
		Given the user launches browser and open JustDial website 
		When the user handles car wash pop ups
		And the user search nearby car washing services
		And the user applies rating filter
		And the user sort top rated service centres
		Then the user should be able to view and print top rated service centres

### ✅ Scenario: Testing Invalid Phone Number Input
- Launching browsers - Cross browser testing
- Handle popups on JustDial homepage
- Submit Free Listing form with invalid phone number
- And the submit it with a valid phone number
- Close browser

### 🧾 Scenario (Cucumber)
```gherkin
	Scenario: Number Verification
		Given the user launches browser and open JustDial website 
		When the user handles car wash pop ups
		And the user navigates to Free Listing
		And the user enters an invalid mobile number "1234567890"
		Then an error message should be displayed
		And the user proceeds with valid mobile number and verifies it

### ✅ Scenario: Testing SubMenus of GymMenu
- Launching browsers - Cross browser testing
- Handle popups on JustDial homepage
- Navigate to Gym section and extract sub-menu items
- Close browser

### 🧾 Scenario (Cucumber)
```gherkin
	Scenario: Getting SubMenu in Gym 
		Given the user launches browser and open JustDial website 
		When the user handles car wash pop ups
		And user navigates to Gym
		Then user prints Gym SubMenu items
