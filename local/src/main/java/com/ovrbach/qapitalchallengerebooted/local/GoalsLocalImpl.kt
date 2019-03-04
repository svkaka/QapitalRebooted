package com.ovrbach.qapitalchallengerebooted.local

import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsLocal
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import com.ovrbach.qapitalchallengerebooted.local.mapper.GoalMapper
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class GoalsLocalImpl @Inject constructor(
    private val database: AppDatabase,
    private val mapper: GoalMapper
) : GoalsLocal {

    override fun getSavedGoals(): Observable<List<Goal>> {
        return database.goals().getSavedGoals()
            .map {
                it.map { entity ->
                    mapper.mapFromEntity(entity)
                }
            }
    }

    override fun clearSavedGoals(): Completable {
        return database.goals().dropTable()
    }

    override fun saveGoals(list: List<Goal>): Completable {
        val mapped = list.map { entity ->
            mapper.mapToEntity(entity)
        }
        return database.goals().insert(mapped)
    }

    override fun updateDatabase(old: List<Goal>, new: List<Goal>): List<Goal> {
        val nonActual = new
            .filter { newGoal -> !old.contains(newGoal) }
        val mapped = nonActual.map { entity ->
            mapper.mapToEntity(entity)
        }
        println("INSERT $nonActual")
        database.goals().insert(mapped).subscribe()
        //todo return completes
        return nonActual
    }

    override fun deleteSavedGoal(goalId: GoalId): Completable {
        return database.goals().delete(goalId)
    }
}