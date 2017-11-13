package view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Justin.Yuan.ClinicalSkillApp.MainActivity;
import com.Justin.Yuan.ClinicalSkillApp.R;

import java.util.ArrayList;
import java.util.List;

import model.CardModel;


public class CardListView extends Fragment {
  private MainActivity mainActivity;
  private RecyclerView mRecyclerView;
  private MyAdapter myAdapter;
  private List<CardModel> actors = new ArrayList<CardModel>();
private int methodIndex;
  public CardListView(List<CardModel> detailModel, MainActivity mainActivity, int methodIndex) {
     this.methodIndex=methodIndex;
      actors=detailModel;
     this.mainActivity=mainActivity;

  }

    public CardListView(MainActivity mainActivity) {
        this.mainActivity=mainActivity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        myAdapter = new MyAdapter(mainActivity.getPresentor(),actors,methodIndex );
        recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        CardView cardView=(CardView) recyclerView.findViewById(R.id.card);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, "This is a card view!", Toast.LENGTH_LONG).show();
//            }
//        });
        return recyclerView;
    }
  /*@Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    mainActivity.setContentView(R.layout.recycler_view);


    // 拿到RecyclerView
   mRecyclerView = (RecyclerView) mainActivity.findViewById(R.id.list);
    // 设置LinearLayoutManager
    mRecyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
    // 设置ItemAnimator
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    // 设置固定大小
    mRecyclerView.setHasFixedSize(true);
      // 初始化自定义的适配器
      myAdapter = new MyAdapter(mainActivity,actors );

    // 为mRecyclerView设置适配器
    mRecyclerView.setAdapter(myAdapter);

  }*/

  public void add(List<CardModel> detailModel) {

  }


}
