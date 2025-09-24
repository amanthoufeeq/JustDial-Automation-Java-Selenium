package Factory;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

public class logger {
	
	public static void info(String log)
	{
		BaseClass.getLogger().info(log);
		ExtentCucumberAdapter.addTestStepLog("Test Log: "+log);
	}
	public static void debug(String log)
	{
		BaseClass.getLogger().debug(log);
		ExtentCucumberAdapter.addTestStepLog("Test Log: "+log);
	}
	public static void warn(String log)
	{
		BaseClass.getLogger().warn(log);
		ExtentCucumberAdapter.addTestStepLog("Test Log: "+log);
	}
	public static void error(String log)
	{
		BaseClass.getLogger().error(log);
		ExtentCucumberAdapter.addTestStepLog("Test Log: "+log);
	}

}
