package com.vladislavlevchik.utils;

import com.vladislavlevchik.entity.Match;
import com.vladislavlevchik.entity.Player;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.configure();

        return configuration.buildSessionFactory();
    }

    private static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Match.class);
        configuration.addAnnotatedClass(Player.class);

        return configuration;
    }

}
