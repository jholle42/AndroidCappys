package jackal.org.cappyapp;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

/**
 * Created by jholle42 on 6/5/18.
 */

public class firebaseDatabaseInteractor {

        private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        private FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance();

        public firebaseDatabaseInteractor() { }


        public void addUserInfo(final AppUser user) {

        }

        private void uploadHold(final String hold) {

        }

        public void updateUser(final AppUser item) {

        }


        public void updateInventoryItem(final AppUser user) {

        }

        public void sendQuery(String query) {

        }

        public void removeQuery() {

        }
    }
