package com.ovrbach.qapitalchallengerebooted.local.dao

import androidx.room.*
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import com.ovrbach.qapitalchallengerebooted.local.entity.GoalEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface GoalDao {

    @Query("SELECT * from goal")
    fun getSavedGoals(): Observable<List<GoalEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(goal: GoalEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(goal: List<GoalEntity>): Completable

    @Delete
    fun delete(goalId: GoalEntity): Completable

    @Query("DELETE FROM goal WHERE id = :id")
    fun delete(id: GoalId): Completable

    @Query("DELETE FROM goal")
    fun dropTable(): Completable
}