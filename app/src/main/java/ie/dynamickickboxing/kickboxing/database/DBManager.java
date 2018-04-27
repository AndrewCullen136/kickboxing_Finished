package ie.dynamickickboxing.kickboxing.database;

/**
 * Created by andrewcullen on 27/04/2018.
 */

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import ie.dynamickickboxing.kickboxing.Base;
import ie.dynamickickboxing.kickboxing.PayScreen;

import ie.dynamickickboxing.kickboxing.Report;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import models.Paid;

public class DBManager {

    public Realm realmDatabase;

    public DBManager(Context context) {
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("donation.realm")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(config);
    }

    public void open() throws SQLException {
        realmDatabase = Realm.getDefaultInstance();
    }

    public void close() {
        realmDatabase.close();
    }

    public void add(Paid d) {
        realmDatabase.beginTransaction();
        realmDatabase.copyToRealm(d);
        realmDatabase.commitTransaction();
    }

    public List<Paid> getAll() {
        RealmResults<Paid> result = realmDatabase.where(Paid.class)
                .findAll();
        return result.subList(0,result.size());
    }

    public Paid get(String id) {
        return realmDatabase.where(Paid.class)
                .equalTo("id",id)
                .findAll()
                .first();
    }

    public void setTotalDonated(Base base) {
        base.app.totalPaid = realmDatabase.where(Paid.class)
                .findAll()
                .sum("amount")
                .intValue();
    }

    public void reset() {
        realmDatabase.beginTransaction();
        realmDatabase.where(Paid.class)
                .findAll()
                .deleteAllFromRealm();
        realmDatabase.commitTransaction();
    }
}
