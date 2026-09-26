package web.minda.project.service;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import web.minda.project.dto.UserCreatedEvent;

@Service
public class UserEventProducer {

	private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

	public UserEventProducer(KafkaTemplate<String, UserCreatedEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publishUserCreated(UserCreatedEvent event) {
		kafkaTemplate.send("user-created", event);
	}
}