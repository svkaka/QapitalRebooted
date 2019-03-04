package com.ovrbach.qapitalchallengerebooted.domain.mapper


interface EntityMapper<Entity, DataEntity> {

    fun mapFromEntity(entity: Entity): DataEntity

    fun mapToEntity(dataEntity: DataEntity): Entity
}