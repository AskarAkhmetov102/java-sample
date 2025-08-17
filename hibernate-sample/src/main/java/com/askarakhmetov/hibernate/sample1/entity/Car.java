package com.askarakhmetov.hibernate.sample1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String vin;

    @Column
    private String color;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "model_name")
    private String modelName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "engine_number")),
            @AttributeOverride(name = "horsePower", column = @Column(name = "engine_horse_power"))
    })
    private Engine engine;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "wheel")
    @AttributeOverride(name = "isWinter", column = @Column(name = "is_winter"))
    private List<Wheel> wheels;

    @ElementCollection
    @CollectionTable(name = "glass")
    @Column(name = "glass_number")
    private List<String> glasses;

}
