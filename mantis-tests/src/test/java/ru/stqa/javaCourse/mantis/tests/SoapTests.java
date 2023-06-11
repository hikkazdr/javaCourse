package ru.stqa.javaCourse.mantis.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.javaCourse.mantis.model.Issue;
import ru.stqa.javaCourse.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends BaseTest {

    @BeforeClass
    public void checkForIssue() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(2);
    }

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue()
                .withSummary("VA Test issue")
                .withDescription("VA Test issue description")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
