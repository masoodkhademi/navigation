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

import jahedmanesh.lib.navigation.item.NavItem;
import jahedmanesh.lib.navigation.item.NavMenuItem;
import jahedmanesh.lib.navigation.item.NavSubHeaderItem;

/**
 * Created by Mehdi Jahed Manesh on 2/28/18 at 5:57 PM.
 * Email : 47even@gmail.com
 */


public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int TYPE_ITEM_MENU = 100;
    final int TYPE_ITEM_SUB_HEADER = 101;
    final int TYPE_ITEM_DIVIDER = 102;

    private List<NavItem> items = new ArrayList<>();
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

            case TYPE_ITEM_SUB_HEADER:

                switch (contentGravity) {
                    case NavigationView.CONTENT_GRAVITY_AUTO:
                        layout = R.layout.jahedmanesh_item_sub_header_auto;
                        break;
                    case NavigationView.CONTENT_GRAVITY_LEFT:
                        layout = R.layout.jahedmanesh_item_sub_header_left;
                        break;
                    case NavigationView.CONTENT_GRAVITY_RIGHT:
                        layout = R.layout.jahedmanesh_item_sub_header_right;
                        break;
                }

                return new SubHeaderItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));

            default:
                return new DividerItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.jahedmanesh_item_divider, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MenuItemViewHolder) {
            NavMenuItem menuItem = (NavMenuItem) items.get(position);
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

        } else if (holder instanceof SubHeaderItemViewHolder) {
            NavSubHeaderItem navSubHeaderItem = (NavSubHeaderItem) items.get(position);
            ((SubHeaderItemViewHolder) holder).tvTitle.setText(navSubHeaderItem.getTitle());
        }  else {
            // TODO: 3/19/18 set properties for divider
        }

    }

    @Override
    public int getItemViewType(int position) {
        NavItem item = items.get(position);
        if (item instanceof NavMenuItem) {
            return TYPE_ITEM_MENU;
        } else  if (item instanceof NavSubHeaderItem){
            return TYPE_ITEM_SUB_HEADER;
        } else {
            return TYPE_ITEM_DIVIDER;
        }
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void replace(List<NavItem> items) {
        this.items.clear();
        this.items.addAll(items);
        refresh();
    }

    public boolean remove(NavItem item) {
        boolean isRemoved = items.remove(item);
        refresh();
        return isRemoved;
    }

    public NavItem remove(int position) {
        NavItem removedItem = items.remove(position);
        refresh();
        return removedItem;
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public void setMenuItemCallback(@Nullable NavigationView.MenuListCallback menuItemCallback) {
        this.menuItemCallback = menuItemCallback;
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

    public class SubHeaderItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        public SubHeaderItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

    }

    public class DividerItemViewHolder extends RecyclerView.ViewHolder {

        View itemDivider;

        public DividerItemViewHolder(View itemView) {
            super(itemView);
            itemDivider = itemView.findViewById(R.id.itemDivider);
        }

    }

}
