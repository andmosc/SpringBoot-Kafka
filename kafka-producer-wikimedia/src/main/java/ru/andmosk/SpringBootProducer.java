package ru.andmosk;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.andmosk.service.WikimediaChangesProducer;

@SpringBootApplication
@AllArgsConstructor
public class SpringBootProducer implements CommandLineRunner
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringBootProducer.class);
    }

    private WikimediaChangesProducer wikimediaChangesProducer;

    @Override
    public void run(String... args) throws Exception {
        wikimediaChangesProducer.sendMessage();
    }
}
