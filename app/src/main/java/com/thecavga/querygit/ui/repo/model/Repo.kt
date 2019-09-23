package com.thecavga.querygit.ui.repo.model
import com.google.gson.annotations.SerializedName

data class Repo (
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("owner") var owner: Owner,
    @SerializedName("stargazers_count") var stargazers_count: Int,
    @SerializedName("open_issues_count") var open_issues_count: Int
)

