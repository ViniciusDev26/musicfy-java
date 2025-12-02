package com.github.viniciusdev26.musicfy.soap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetPlaylistsRequest")
@XmlType(name = "GetPlaylistsRequest", propOrder = {"userId", "systemOnly", "page", "pageSize"})
public class GetPlaylistsRequest {

    @XmlElement
    private Long userId;

    @XmlElement
    private Boolean systemOnly;

    @XmlElement
    private Integer page;

    @XmlElement
    private Integer pageSize;
}
