package models;


import java.util.UUID;

import io.realm.RealmObject;

public class Paid extends RealmObject
    {
        public String id;
        public int    amount;
        public String method;


        public Paid() {}

        public Paid (int amount, String method)
        {
            this.id = UUID.randomUUID().toString();
            this.amount = amount;
            this.method = method;
        }

        @Override
        public String toString() {
            return "Donation{" +
                    "id = " + id +
                    "amount=$" + amount +
                    ", method='" + method + '\'' +
                    '}';
        }
    }