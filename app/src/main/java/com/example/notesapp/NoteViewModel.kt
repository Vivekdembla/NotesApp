package com.example.notesapp

import android.app.Application
import androidx.lifecycle.*
import com.example.notes.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes:LiveData<List<Note>>

    private val repository: NoteRepository

    init {
        val dao=NoteDataBase.getDatabase(application).getNoteDao()
        repository= NoteRepository(dao)
        allNotes=repository.allNotes
    }
    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }

    fun insertNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}