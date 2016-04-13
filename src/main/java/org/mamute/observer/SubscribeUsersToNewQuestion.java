package org.mamute.observer;

import org.mamute.dao.UserDAO;
import org.mamute.dao.WatcherDAO;
import org.mamute.event.QuestionCreated;
import org.mamute.mail.NewQuestionMailer;
import org.mamute.mail.action.EmailAction;
import org.mamute.model.Question;
import org.mamute.model.Tag;
import org.mamute.model.User;
import org.mamute.model.watch.Watcher;
import org.mamute.notification.NotificationMail;
import org.mamute.notification.NotificationMailer;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SubscribeUsersToNewQuestion {

	@Inject
	private UserDAO users;
	@Inject
	private WatcherDAO watchers;
	@Inject
	private NewQuestionMailer newQuestionMailer;

	public void subscribeUsers(@Observes QuestionCreated questionCreated) {
		Question question = questionCreated.getQuestion();
		List<User> subscribed = users.findUsersSubscribedToAllQuestions();
		List<Tag> tags = question.getTags();

		Set<User> usersSet = new HashSet<>();
		for(Tag tag : tags){
			Set<User> allSubscribers = tag.getAllSubscribers();
			usersSet.addAll(allSubscribers);
		}
		usersSet.addAll(subscribed);

		for (User user : subscribed) {
			watchers.add(question, new Watcher(user));
		}

		List<User> mailList = new LinkedList<>(usersSet);
		newQuestionMailer.send(mailList, question);

	}
}
