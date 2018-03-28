package com.newport.app.ui.newdetail;

import com.newport.app.data.models.response.NewResponse;

/**
 * Created by tohure on 17/11/17.
 */

class   NewDetailPresenter implements NewDetailContract.Presenter, NewDetailContract.Callback {

    private NewDetailContract.View newView;

    @Override
    public void makeNewQuery(int idNew) {
        newView.showLoading();
        NewDetailInteractor.getNew(idNew, this);
    }

    @Override
    public void attachedView(NewDetailContract.View newview) {
        this.newView = newview;
    }

    @Override
    public void detachView() {
        newView = null;
    }

    @Override
    public void getNewSucces(NewResponse newResponse) {

        String text;
        text = "<html>";
        text += "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, user-scalable=yes\" />" +
                "<meta name=\\\"viewport\\\" content=\\\"target-densitydpi=high-dpi\\\" />" +
                "<meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1\\\">\n" +
                "<link rel=\"stylesheet\" media=\"screen and (-webkit-device-pixel-ratio:1.5)\" href=\"hdpi.css\" />" +
                "</head>" +
                "<body>";
        text += newResponse.getContent();
        text += "</body></html>";

        newResponse.setContent(text);

        newView.showNew(newResponse);
        newView.hideLoading();
    }

    @Override
    public void getNewError(String error) {
        newView.showNewError(error);
        newView.hideLoading();
    }

    @Override
    public void getNewFailure(String message) {
        newView.showNewError(message);
        newView.hideLoading();
    }
}
