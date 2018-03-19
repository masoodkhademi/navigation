package jahedmanesh.lib.navigation.item;

/**
 * Created by Mehdi Jahed Manesh on 2/28/18 at 8:25 PM.
 * Email : 47even@gmail.com
 */
public abstract class NavItem {

    private long id;

    NavItem(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NavItem)) return false;

        NavItem item = (NavItem) o;

        return getId() == item.getId();
    }
}
