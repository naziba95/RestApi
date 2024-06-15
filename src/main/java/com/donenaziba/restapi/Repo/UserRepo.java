package com.donenaziba.restapi.Repo;

import com.donenaziba.restapi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>
{

}
