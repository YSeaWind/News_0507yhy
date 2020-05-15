package com.example.aNews_0507yhy.utils.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aNews_0507yhy.R;
import com.example.aNews_0507yhy.utils.childutils.ChildNewsMessage;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ChildNewsMessage> userList;//存放要加载的item控件，便于调用   数组类型是要写你自己存放数据的类
    private Context context;//在服务里没有上下文环境，给一个上下文环境



    public MyRecyclerViewAdapter2(List<ChildNewsMessage> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }
    /**
     * 创建viewholder内部类 缓存item内的控件
     */
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        //itemView 表示item布局
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);//找到对应的item要加载的内容
            textView = itemView.findViewById(R.id.tv);


        }
    }
    class SecondViewHolder extends RecyclerView.ViewHolder{
        TextView textView2;
        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);
            textView2 = itemView.findViewById(R.id.tv_r_item);
        }
    }
    //自定义点击item事件的方法
    private OnItemClickListener onItemClickListener;
    public void  setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 自定义的回调接口
     */

    public interface OnItemClickListener{
        void OnItemClick(View view);
}
    /**
     * 创建viewholder的实例对象，并把加载的布局传入到构造函数中，然后再返回viewholder实例
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 100) {
            View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item2, null);
            //获取返回值viewHolder
            ViewHolder viewHolder = new ViewHolder(view);
            //itemview表示每一个item布局对象   给item对象设置点击事件
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(view);
                }
            });
            return viewHolder;//利用系统的点击事件赋给item
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, null);
            SecondViewHolder secondViewHolder = new SecondViewHolder(view);
            return secondViewHolder;
        }




    }



    /**
     * 用于给viewholder中的子控件进行赋值（view和数据绑定的过程）
     * 在每个子项被滚动到屏幕内的时候执行
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 100:
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.imageView.setImageResource(userList.get(position).getImg());//给图片赋值
                viewHolder.textView.setText(userList.get(position).getUsername());
                break;
            case 200:
                SecondViewHolder secondViewHolder = (SecondViewHolder) holder;
                secondViewHolder.textView2.setText("857857857857"+position);
                break;
        }


    }

    @NonNull
    /**
     * 设置item的个数
     */
    public int getItemCount() {
        return userList!=null?userList.size():0;
    }

    /**
     *
     * @param position item的索引值
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position%3==1){
            return 100;
        }else {
            return 200;
        }

    }

}
