package org.adilEfqan.tinder.DBServer;


import org.adilEfqan.tinder.DAO.LikeMapper;
import org.adilEfqan.tinder.DAO.MessageWrapper;
import org.adilEfqan.tinder.DAO.UserMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;


public class PostgresServer {


    public SqlSession createConnection(String url, String name, String password) {
        DataSource dataSource = PostgresServer.getDataSource(url, name, password);
        TransactionFactory factory = new JdbcTransactionFactory();
        Environment environment =
                new Environment("development", factory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        configuration.addMapper(MessageWrapper.class);
        configuration.addMapper(LikeMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory.openSession();
    }

    private static DataSource getDataSource(String URL, String NAME, String PASSWORD){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
}
