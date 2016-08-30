package co.sspp.goodserapp.weight.rv.app;


import co.sspp.goodserapp.weight.rv.SwipeRefreshListView;

public abstract class SwipeRefreshListFragment extends SwipeRefreshAbsListFragment<SwipeRefreshListView> {

    @Override
    public SwipeRefreshListView createSwipeRefreshLayout() {
        return new SwipeRefreshListView(getActivity());
    }
}
