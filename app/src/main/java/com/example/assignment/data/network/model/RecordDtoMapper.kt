package com.example.assignment.data.network.model

import com.example.assignment.data.domain.model.Record
import com.example.assignment.data.domain.util.DomainMapper
import javax.inject.Inject

class RecordDtoMapper
@Inject
constructor() : DomainMapper<RecordDto, Record> {
    override fun mapToDomainModel(model: RecordDto): Record {
        return Record(
            siteName = model.siteName,
            county = model.county,
            aqi = model.aqi,
            pollutant = model.pollutant,
            status = model.status,
            so2 = model.so2,
            co = model.co,
            o3 = model.o3,
            o3_8hr = model.o3_8hr,
            pm10 = model.pm10,
            pm2_5 = model.pm2_5?.toIntOrNull(),
            no2 = model.no2,
            nox = model.nox,
            windSpeed = model.windSpeed,
            windDirect = model.windDirect,
            publishTime = model.publishTime,
            co_8hr = model.co_8hr,
            pm2_5_Avg = model.pm2_5_avg,
            pm10_Avg = model.pm10_avg,
            so2_Avg = model.so2_avg,
            longitude = model.longitude,
            latitude = model.latitude,
            sitedId = model.siteId?.toIntOrNull()
        )
    }

    override fun mapFromDomainModel(domainModel: Record): RecordDto {
        return RecordDto(
            siteName = domainModel.siteName,
            county = domainModel.county,
            aqi = domainModel.aqi,
            pollutant = domainModel.pollutant,
            status = domainModel.status,
            so2 = domainModel.so2,
            co = domainModel.co,
            o3 = domainModel.o3,
            o3_8hr = domainModel.o3_8hr,
            pm10 = domainModel.pm10,
            pm2_5 = domainModel.pm2_5.toString(),
            no2 = domainModel.no2,
            nox = domainModel.nox,
            windSpeed = domainModel.windSpeed,
            windDirect = domainModel.windDirect,
            publishTime = domainModel.publishTime,
            co_8hr = domainModel.co_8hr,
            pm2_5_avg = domainModel.pm2_5_Avg,
            pm10_avg = domainModel.pm10_Avg,
            so2_avg = domainModel.so2_Avg,
            longitude = domainModel.longitude,
            latitude = domainModel.latitude,
            siteId = domainModel.sitedId.toString()
        )
    }

    fun toDomainList(initial: List<RecordDto>): List<Record> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Record>): List<RecordDto> {
        return initial.map { mapFromDomainModel(it) }
    }

}