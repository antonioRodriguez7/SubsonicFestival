package com.susbsonic.usuarios.Repositories;

import com.susbsonic.usuarios.models.Faq;
import com.susbsonic.usuarios.models.Faq.TargetRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {
    List<Faq> findByTargetRoleOrderByOrdenAsc(TargetRole targetRole);
}
