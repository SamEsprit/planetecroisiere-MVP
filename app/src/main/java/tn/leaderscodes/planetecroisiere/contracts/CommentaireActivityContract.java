package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.Commentaire;
import tn.leaderscodes.planetecroisiere.remote.request.CommentsRequest;

public class CommentaireActivityContract {

    public interface View {
        void showComments(List<Commentaire> commentaires);

        void addComments(Commentaire commentaire);

    }

    public interface Presenter {
        void do_addComments(String id, CommentsRequest commentsRequest);

        void do_showComments(String id);
    }
}
