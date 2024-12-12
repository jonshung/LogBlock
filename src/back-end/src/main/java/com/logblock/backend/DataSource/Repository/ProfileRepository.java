package com.logblock.backend.DataSource.Repository;

import com.logblock.backend.DataSource.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    // Tìm kiếm hồ sơ theo userID
    Profile findByUserID(int userID);

}
