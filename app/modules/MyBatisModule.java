package modules;


import com.google.inject.name.Names;
import daos.UsersDao;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import play.db.Database;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.sql.DataSource;

public class MyBatisModule extends org.mybatis.guice.MyBatisModule {


    @Override
    protected void initialize() {

        environmentId("development");
        bindConstant().annotatedWith(
                Names.named("mybatis.configuration.failFast")).
                to(true);
        bindDataSourceProviderType(PlayDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);

        useCacheEnabled(false);
        addMapperClass(UsersDao.class);

    }

    /* Provides a {@link DataSource} from the {@link Database} which can be injected from Play. */
    @Singleton
    public static class PlayDataSourceProvider implements Provider<DataSource> {
        final Database db;

        @Inject
        public PlayDataSourceProvider(final Database db) {
            this.db = db;
        }


        @Override
        public DataSource get() {
            return db.getDataSource();
        }
    }
}
