package online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.ViewModel;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;

import java.util.List;

import online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Presenter.SearchMedicinePresenter;
import online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository.MedicineDBHandler;
import online.ghuri.com.beacondoctorapp.GenericAndBrandSearch.Repository.MedicineInfo;
import online.ghuri.com.beacondoctorapp.R;
import online.ghuri.com.beacondoctorapp.databinding.FragmentJournalBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMedicineFragment extends Fragment implements SearchMedicineViewModel{

    private FragmentJournalBinding mBinding;
    private SearchMedicinePresenter mPresenter;
    private SearchViewAdapter mAdapter;
    public SearchMedicineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_journal, container, false);
        mPresenter=new SearchMedicinePresenter(this,new MedicineDBHandler(getActivity()));

        mBinding.floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                mPresenter.findMedicineInfo(newQuery);
            }
        });


        mBinding.floatingSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.generic_menu_item:
                        mPresenter.onGenericMenuItemClick();
                        break;
                    case R.id.brand_menu_item:
                        mPresenter.onBrandMenuItemClick();
                        break;
                    case R.id.company_menu_item:
                        mPresenter.onCompanyMenuItemClick();
                        break;
                }
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void showSearchProgress() {
        mBinding.floatingSearchView.showProgress();
    }

    @Override
    public void hideSearchProgress() {
        mBinding.floatingSearchView.hideProgress();
    }

    @Override
    public void clearSearchSuggestion() {

            mBinding.floatingSearchView.clearSuggestions();
    }

    @Override
    public void searchBarTitle(String word) {
        mBinding.floatingSearchView.setSearchBarTitle("");
        mBinding.floatingSearchView.setSearchHint(word);
    }

    @Override
    public void showMedicineSearchResult(List<MedicineInfo> medicineInfoList) {
        if (mAdapter==null){
            mAdapter=new SearchViewAdapter(medicineInfoList);
            mBinding.journalRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            mBinding.journalRecycler.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setSearchBarHint(String hint) {
        mBinding.floatingSearchView.setSearchHint(hint);
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder{
        TextView nameTV,priceTv,genericTv,companyTv;
        View view;
        public SearchViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            nameTV=itemView.findViewById(R.id.name_tv);
            priceTv=itemView.findViewById(R.id.price_tv);
            genericTv=itemView.findViewById(R.id.generic_tv);
            companyTv=itemView.findViewById(R.id.company_name_tv);
        }
    }

    public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewHolder>{
        private List<MedicineInfo> mMedicineInfos;

        public SearchViewAdapter(List<MedicineInfo> mMedicineInfos) {
            this.mMedicineInfos = mMedicineInfos;
        }

        @Override
        public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getActivity()).inflate(R.layout.row_search_medicine_result,parent,false);
            return new SearchViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SearchViewHolder holder, int position) {
            MedicineInfo info=mMedicineInfos.get(position);
            holder.nameTV.setText(info.getName());
            holder.genericTv.setText(info.getGeneric());
            holder.companyTv.setText(info.getCompany());

            if (info.getCompany().equals("Beacon Pharmaceuticals Ltd.")){
                holder.view.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.color_highlight));

            }else {
                holder.view.setBackgroundColor(ContextCompat.getColor(getActivity(),android.R.color.white));
            }

        }

        @Override
        public int getItemCount() {
            return mMedicineInfos.size();
        }
    }

}
