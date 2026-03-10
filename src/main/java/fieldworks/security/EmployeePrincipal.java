package fieldworks.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fieldworks.models.Employee;

public class EmployeePrincipal implements UserDetails {

    private Employee emp;

    public EmployeePrincipal(Employee emp) {
        this.emp = emp;
    }
    
    // get role from db
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + emp.getPrivilege().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return emp.getPassword();
    }

    @Override
    public String getUsername() {
        return emp.getUserName();
    }

    @Override
    public boolean isEnabled() {
        return emp.getActive();
    }

    public String getPrivilege() {
        return emp.getPrivilege();
    }
}

