package com.susbsonic.usuarios.models.DTO;

import com.susbsonic.usuarios.models.Faq.TargetRole;
import lombok.Data;

@Data
public class FaqDTO {
    private Long id;
    private String pregunta;
    private String respuesta;
    private TargetRole targetRole;
    private Integer orden;
}
