package me.smash.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class MyReporter implements IReporter {

  @Override
  public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
      String outputDirectory) {
    try (PrintStream out = new PrintStream(new FileOutputStream(new File(outputDirectory, "my-report.txt")))) {
      suites.forEach(s -> {
        out.println("Suite: " + s.getName());
        s.getResults().forEach((k, v) -> {
          ITestContext context = v.getTestContext();
          out.println("\tTest: " + k);
          out.println("\t\tFailed tests:");
          context.getFailedTests().getAllMethods()
              .forEach(m -> out.println("\t\t\t" + m.getRealClass().getCanonicalName() + "::" + m.getMethodName()));

          out.println("\t\tPassed tests:");
          context.getPassedTests().getAllMethods()
              .forEach(m -> out.println("\t\t\t" + m.getRealClass().getCanonicalName() + "::" + m.getMethodName()));

          out.println("\t\tExcluded methods:");
          context.getExcludedMethods()
              .forEach(m -> out.println("\t\t\t" + m.getRealClass().getCanonicalName() + "::" + m.getMethodName()));
        });

      });

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


  }
}
