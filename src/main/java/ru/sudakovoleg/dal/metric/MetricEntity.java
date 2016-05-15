package ru.sudakovoleg.dal.metric;

/**
 * Created by Oleg on 05.05.2016.
 */
public class MetricEntity {
    private Long id;
    private String name;
    private Long dataTypeId;

    public MetricEntity(Long id, String name, Long dataTypeId){
        this.id = id;
        this.name = name;
        this.dataTypeId = dataTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Long dataTypeId) {
        this.dataTypeId = dataTypeId;
    }
}
