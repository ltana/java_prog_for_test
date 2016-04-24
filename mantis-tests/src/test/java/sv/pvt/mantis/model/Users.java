package sv.pvt.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ltana on 25.04.2016.
 */
public class Users extends ForwardingSet<UsersData> {

    private Set<UsersData> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<UsersData>(users.delegate);
    }

    public Users() {
        this.delegate = new HashSet<UsersData>();
    }

    public Users(Collection<UsersData> contacts) {
        this.delegate = new HashSet<UsersData>(contacts);
    }

    @Override
    protected Set<UsersData> delegate() {
        return delegate;
    }
}

