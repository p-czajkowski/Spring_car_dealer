package pl.sdacademy.spring.car_dealer.model;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
