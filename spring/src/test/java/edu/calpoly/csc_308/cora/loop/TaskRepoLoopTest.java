package edu.calpoly.csc_308.cora.loop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskRepository;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskDAO;
import edu.calpoly.csc_308.cora.api.request.VolunteerFilterRequestModel;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TaskRepoLoopTest {
  
  @Autowired
  private VolunteerTaskRepository repo;

  private ArrayList<VolunteerTaskDAO> tasks = new ArrayList<>();

  @BeforeEach
  void setup() {
    repo.deleteAll();

    tasks.add(new VolunteerTaskDAO(
      new VolunteerTaskDAO.TaskProfile(
        "Supply Collector",
        "Long Beach, CA",
        "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
        "No instructions."
      ),
      1,
      3L,
      "",
      -1L
    ));
    tasks.add( new VolunteerTaskDAO(
      new VolunteerTaskDAO.TaskProfile(
        "Pamphlet Designer",
        "Long Beach, CA",
        "A graphic designer is needed to assist in the creation of informational brocures and pamphlets that will help inform the community on how to stay safe during the COVID pandemic.",
        "Message me for more instructions."
      ),
      0,
      4L,
      "Graphic Design",
      -1L
    ));

    repo.saveAll(tasks);
  }

  @AfterEach
  void teardown() {
    repo.deleteAll();
  }

  @Test
  void testTaskRepoLoopZeroTimes() {
    VolunteerFilterRequestModel filters = new VolunteerFilterRequestModel();
    filters.setEnabledFilters(new String[]{});

    List<VolunteerTaskDAO> foundTasks = repo.tasksByFilters(filters);

    assertThat(foundTasks, contains(tasks.toArray()));
  }

  @Test
  void testTaskRepoLoopOneTime() {
    VolunteerFilterRequestModel filters = new VolunteerFilterRequestModel();
    filters.setEnabledFilters(new String[]{"skillSet"});
    filters.setSkillSet(new String[]{"Graphic Design"});

    List<VolunteerTaskDAO> foundTasks = repo.tasksByFilters(filters);

    assertThat(foundTasks, contains(new VolunteerTaskDAO[]{tasks.get(1)}));
  }

  @Test
  void testTaskRepoLoopTypicalTimes() {
    VolunteerFilterRequestModel filters = new VolunteerFilterRequestModel();
    filters.setEnabledFilters(new String[]{"skillSet", "need"});
    filters.setSkillSet(new String[]{"Graphic Design"});
    filters.setNeed(0);

    List<VolunteerTaskDAO> foundTasks = repo.tasksByFilters(filters);

    assertThat(foundTasks, contains(new VolunteerTaskDAO[]{tasks.get(1)}));
  }

  @Test
  void testTaskRepoLoopNMinusOneTimes() {
    VolunteerFilterRequestModel filters = new VolunteerFilterRequestModel();
    filters.setEnabledFilters(new String[]{"skillSet", "need", "location"});
    filters.setSkillSet(new String[]{"Graphic Design"});
    filters.setNeed(0);
    filters.setLocation("AZ");

    List<VolunteerTaskDAO> foundTasks = repo.tasksByFilters(filters);

    assertThat(foundTasks, is(empty()));
  }

  @Test
  void testTaskRepoLoopNTimes() {
    VolunteerFilterRequestModel filters = new VolunteerFilterRequestModel();
    filters.setEnabledFilters(new String[]{"skillSet", "need", "location", "search"});
    filters.setSkillSet(new String[]{"Graphic Design"});
    filters.setNeed(0);
    filters.setLocation("CA");
    filters.setSearch("pamphlet");

    List<VolunteerTaskDAO> foundTasks = repo.tasksByFilters(filters);

    assertThat(foundTasks, contains(new VolunteerTaskDAO[]{tasks.get(1)}));
  }

  @Test
  void testTaskRepoLoopNPlusOneTimes() {
    VolunteerFilterRequestModel filters = new VolunteerFilterRequestModel();
    filters.setEnabledFilters(new String[]{"skillSet", "need", "location", "search", "skillSet"});
    filters.setSkillSet(new String[]{"Graphic Design"});
    filters.setNeed(0);
    filters.setLocation("CA");
    filters.setSearch("pamphlet");

    List<VolunteerTaskDAO> foundTasks = repo.tasksByFilters(filters);

    assertThat(foundTasks, contains(new VolunteerTaskDAO[]{tasks.get(1)}));
  }

  @Test
  void testTaskRepoLoopNPlusOneTimesInvalidFilter() {
    VolunteerFilterRequestModel filters = new VolunteerFilterRequestModel();
    filters.setEnabledFilters(new String[]{"skillSet", "need", "location", "search", "skillSet", "some invalid filter"});
    filters.setSkillSet(new String[]{"Graphic Design"});
    filters.setNeed(0);
    filters.setLocation("CA");
    filters.setSearch("pamphlet");

    List<VolunteerTaskDAO> foundTasks = repo.tasksByFilters(filters);

    assertThat(foundTasks, contains(new VolunteerTaskDAO[]{tasks.get(1)}));
  }

}
