Feature: Car Wash
	Scenario: Car Wash details extraction
		Given the user launches browser and open JustDial website 
		When the user handles car wash pop ups
		And the user search nearby car washing services
		And the user applies rating filter
		And the user sort top rated service centres
		Then the user should be able to view and print top rated service centres
