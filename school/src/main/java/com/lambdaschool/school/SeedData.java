package com.lambdaschool.school;


import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Role;
import com.lambdaschool.school.model.User;
import com.lambdaschool.school.model.UserRoles;
import com.lambdaschool.school.repository.CourseRepository;
import com.lambdaschool.school.repository.RoleRepository;
import com.lambdaschool.school.repository.UserRepository;
//import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;


@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;
    CourseRepository courserepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos, CourseRepository courserepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.courserepos = courserepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {

        Role r2 = new Role("user");





        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));


        rolerepos.save(r2);

        User u1 = new User("sam", "gottem", users);








        userrepos.save(u1);

        //        userrepos.save(u3);
        //        userrepos.save(u4);
        System.out.println(u1.getUsername());

    }
}