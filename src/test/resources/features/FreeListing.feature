Feature: Free Listing
	Scenario: Number Verification
		Given the user launches browser and open JustDial website 
		When the user handles car wash pop ups
		And the user navigates to Free Listing
		And the user enters an invalid mobile number "1234567890"
		Then an error message should be displayed
		And the user proceeds with valid mobile number and verifies it
		