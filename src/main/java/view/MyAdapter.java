package view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Justin.Yuan.ClinicalSkillApp.R;

import java.util.List;

import model.CardModel;
import presentor.Presentor;

/**
 * Created by RUI on 2017/5/28.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{

    public void setActors(List<CardModel> actors) {
        this.actors = actors;
    }

    private List<CardModel> actors;
    private int methodIndex;
    private Presentor presentor;

    public MyAdapter(Presentor presentor , List<CardModel> actors,int methodIndex)
    {
        this.presentor = presentor;
        this.actors = actors;
        this.methodIndex = methodIndex;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i )
    {
        // 给ViewHolder设置布局文件
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(v,presentor,methodIndex);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i )
    {
        // 给ViewHolder设置元素
        CardModel p = actors.get(i);
        viewHolder.cardIndex=i;
        viewHolder.title.setText(p.getTitle());
        viewHolder.brief.setText(p.getContent());
        viewHolder.image.setImageBitmap(p.getPic());
        // viewHolder.mImageView.setImageDrawable(mContext.getDrawable(p.getImageResourceId(mContext)));
    }

    @Override
    public int getItemCount()
    {
        // 返回数据总数
        return actors == null ? 0 : actors.size();
    }

    public List<CardModel> getActors() {
        return actors;
    }

    // 重写的自定义ViewHolder
    public static class ViewHolder
            extends RecyclerView.ViewHolder
    {
        public TextView title;
        public TextView brief;
        public int cardIndex;
        public ImageView image;

        public ViewHolder(View v , final Presentor presentor, final int methodIndex)
        {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            brief=(TextView) v.findViewById(R.id.brief);
            image = (ImageView) v.findViewById(R.id.image);
            CardView cardView=(CardView) v.findViewById(R.id.card);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(c, "This is a card view!", Toast.LENGTH_LONG).show();
                    presentor.click(v,methodIndex,cardIndex);
                }
            });
            brief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(c, "This is a card view!", Toast.LENGTH_LONG).show();
                presentor.click(v,methodIndex,cardIndex);
            }
        });
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(c, "This is a card view!", Toast.LENGTH_LONG).show();
                    presentor.click(v,methodIndex,cardIndex);
                }
            });
        }
    }
}
