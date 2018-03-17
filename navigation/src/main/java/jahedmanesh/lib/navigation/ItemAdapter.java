package jahedmanesh.lib.navigation;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jahedmanesh.lib.navigation.item.DividerItem;
import jahedmanesh.lib.navigation.item.Item;
import jahedmanesh.lib.navigation.item.MenuItem;

/**
 * Created by Mehdi Jahed Manesh on 2/28/18 at 5:57 PM.
 * Email : 47even@gmail.com
 */


public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int TYPE_ITEM_DIVIDER = 100;
    final int TYPE_ITEM_MENU = 101;

    private List<Item> items = new ArrayList<>();
    private int contentGravity;
    @Nullable
    private NavigationView.MenuListCallback menuItemCallback;

    public ItemAdapter(
            int contentGravity) {
        this.contentGravity = contentGravity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layout = 0;

        switch (viewType) {

            case TYPE_ITEM_MENU:

                switch (contentGravity) {
                    case NavigationView.CONTENT_GRAVITY_AUTO:
                        layout = R.layout.jahedmanesh_item_menu_auto;
                        break;
                    case NavigationView.CONTENT_GRAVITY_LEFT:
                        layout = R.layout.jahedmanesh_item_menu_left;
                        break;
                    case NavigationView.CONTENT_GRAVITY_RIGHT:
                        layout = R.layout.jahedmanesh_item_menu_right;
                        break;
                }

                return new MenuItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));

            default:
                return new DividerItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.jahedmanesh_item_divider, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MenuItemViewHolder) {
            MenuItem menuItem = (MenuItem) items.get(position);
            ((MenuItemViewHolder) holder).imgIcon.setImageResource(menuItem.getIcon());
            ((MenuItemViewHolder) holder).tvTitle.setText(menuItem.getTitle());

            ((MenuItemViewHolder) holder).itemView.setOnClickListener(row -> {
                if (menuItemCallback != null) {
                    menuItemCallback.onMenuItemClicked(row, menuItem, position);
                }
            });

            ((MenuItemViewHolder) holder).itemView.setOnLongClickListener(row -> {
                if (menuItemCallback != null) {
                    menuItemCallback.onMenuItemLongClicked(row, menuItem, position);
                }
                return true;
            });

        }  else {
            DividerItem dividerItem = (DividerItem) items.get(position);
            ((DividerItemViewHolder) holder).itemDivider.setBackgroundColor(dividerItem.getDividerColor());
        }

    }

    @Override
    public int getItemViewType(int position) {
        Item item = items.get(position);
        if (item instanceof MenuItem) {
            return TYPE_ITEM_MENU;
        } else {
            return TYPE_ITEM_DIVIDER;
        }
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void replace(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
        refresh();
    }

    public boolean remove(Item item) {
        boolean isRemoved = items.remove(item);
        refresh();
        return isRemoved;
    }

    public Item remove(int position) {
        Item removedItem = items.remove(position);
        refresh();
        return removedItem;
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public void setMenuItemCallback(@Nullable NavigationView.MenuListCallback menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
    }

    public class DividerItemViewHolder extends RecyclerView.ViewHolder {

        View itemDivider;

        public DividerItemViewHolder(View itemView) {
            super(itemView);
            itemDivider = itemView.findViewById(R.id.itemDivider);
        }

    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView tvTitle;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

    }

}
