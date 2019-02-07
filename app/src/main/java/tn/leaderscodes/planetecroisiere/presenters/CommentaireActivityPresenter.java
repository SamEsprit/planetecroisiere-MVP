package tn.leaderscodes.planetecroisiere.presenters;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.CommentaireActivityContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.Commentaire;
import tn.leaderscodes.planetecroisiere.remote.request.CommentsRequest;
import tn.leaderscodes.planetecroisiere.remote.services.PublicationService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class CommentaireActivityPresenter implements CommentaireActivityContract.Presenter {

    private CommentaireActivityContract.View view;
    private PublicationService publicationService;
    private CompositeDisposable mCompositeDisposable;

    public CommentaireActivityPresenter(CommentaireActivityContract.View view) {
        this.view = view;
        publicationService = Api.createRetrofitService(PublicationService.class);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void do_addComments(String id, CommentsRequest commentsRequest) {
        mCompositeDisposable.add(publicationService.addComment(Session.getInstance().getToken(), id, commentsRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleCommentAdded, this::handleError));
    }


    private void handleCommentAdded(Commentaire commentaire) {
        view.addComments(commentaire);
    }

    private void handleError(Throwable throwable) {
    }

    @Override
    public void do_showComments(String id) {
        mCompositeDisposable.add(publicationService.commentList(Session.getInstance().getToken(), id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleCommentList, this::handleError));
    }

    private void handleCommentList(List<Commentaire> commentaires) {
        view.showComments(commentaires);
    }
}
