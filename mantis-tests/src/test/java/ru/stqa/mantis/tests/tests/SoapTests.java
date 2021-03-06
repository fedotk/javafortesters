package ru.stqa.mantis.tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.mantis.tests.model.Issue;
import ru.stqa.mantis.tests.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by Ксюшенька on 23.04.2016.
 */
public class SoapTests extends TestBase {
    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(1);
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(2);
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), created.getSummary());
    }
}
