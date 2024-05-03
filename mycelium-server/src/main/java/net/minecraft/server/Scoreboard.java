package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Scoreboard {
  private final Map<String, ScoreboardObjective> objectivesByName = Maps.newHashMap();
  
  private final Map<IScoreboardCriteria, List<ScoreboardObjective>> objectivesByCriteria = Maps.newHashMap();
  
  private final Map<String, Map<ScoreboardObjective, ScoreboardScore>> playerScores = Maps.newHashMap();
  
  private final ScoreboardObjective[] displaySlots = new ScoreboardObjective[19];
  
  private final Map<String, ScoreboardTeam> teamsByName = Maps.newHashMap();
  
  private final Map<String, ScoreboardTeam> teamsByPlayer = Maps.newHashMap();
  
  public ScoreboardObjective getObjective(String paramString) {
    return this.objectivesByName.get(paramString);
  }
  
  public ScoreboardObjective registerObjective(String paramString, IScoreboardCriteria paramIScoreboardCriteria) {
    if (paramString.length() > 16)
      throw new IllegalArgumentException("The objective name '" + paramString + "' is too long!"); 
    ScoreboardObjective scoreboardObjective = getObjective(paramString);
    if (scoreboardObjective != null)
      throw new IllegalArgumentException("An objective with the name '" + paramString + "' already exists!"); 
    scoreboardObjective = new ScoreboardObjective(this, paramString, paramIScoreboardCriteria);
    List<ScoreboardObjective> list = this.objectivesByCriteria.get(paramIScoreboardCriteria);
    if (list == null) {
      list = Lists.newArrayList();
      this.objectivesByCriteria.put(paramIScoreboardCriteria, list);
    } 
    list.add(scoreboardObjective);
    this.objectivesByName.put(paramString, scoreboardObjective);
    handleObjectiveAdded(scoreboardObjective);
    return scoreboardObjective;
  }
  
  public Collection<ScoreboardObjective> getObjectivesForCriteria(IScoreboardCriteria paramIScoreboardCriteria) {
    Collection collection = this.objectivesByCriteria.get(paramIScoreboardCriteria);
    return (collection == null) ? Lists.newArrayList() : Lists.newArrayList(collection);
  }
  
  public boolean b(String paramString, ScoreboardObjective paramScoreboardObjective) {
    Map map = this.playerScores.get(paramString);
    if (map == null)
      return false; 
    ScoreboardScore scoreboardScore = (ScoreboardScore)map.get(paramScoreboardObjective);
    if (scoreboardScore == null)
      return false; 
    return true;
  }
  
  public ScoreboardScore getPlayerScoreForObjective(String paramString, ScoreboardObjective paramScoreboardObjective) {
    if (paramString.length() > 40)
      throw new IllegalArgumentException("The player name '" + paramString + "' is too long!"); 
    Map<ScoreboardObjective, ScoreboardScore> map = this.playerScores.get(paramString);
    if (map == null) {
      map = Maps.newHashMap();
      this.playerScores.put(paramString, map);
    } 
    ScoreboardScore scoreboardScore = map.get(paramScoreboardObjective);
    if (scoreboardScore == null) {
      scoreboardScore = new ScoreboardScore(this, paramScoreboardObjective, paramString);
      map.put(paramScoreboardObjective, scoreboardScore);
    } 
    return scoreboardScore;
  }
  
  public Collection<ScoreboardScore> getScoresForObjective(ScoreboardObjective paramScoreboardObjective) {
    ArrayList<ScoreboardScore> arrayList = Lists.newArrayList();
    for (Map<ScoreboardObjective, ScoreboardScore> map : this.playerScores.values()) {
      ScoreboardScore scoreboardScore = (ScoreboardScore)map.get(paramScoreboardObjective);
      if (scoreboardScore != null)
        arrayList.add(scoreboardScore); 
    } 
    Collections.sort(arrayList, ScoreboardScore.a);
    return arrayList;
  }
  
  public Collection<ScoreboardObjective> getObjectives() {
    return this.objectivesByName.values();
  }
  
  public Collection<String> getPlayers() {
    return this.playerScores.keySet();
  }
  
  public void resetPlayerScores(String paramString, ScoreboardObjective paramScoreboardObjective) {
    if (paramScoreboardObjective == null) {
      Map map = this.playerScores.remove(paramString);
      if (map != null)
        handlePlayerRemoved(paramString); 
    } else {
      Map map = this.playerScores.get(paramString);
      if (map != null) {
        ScoreboardScore scoreboardScore = (ScoreboardScore)map.remove(paramScoreboardObjective);
        if (map.size() < 1) {
          Map map1 = this.playerScores.remove(paramString);
          if (map1 != null)
            handlePlayerRemoved(paramString); 
        } else if (scoreboardScore != null) {
          a(paramString, paramScoreboardObjective);
        } 
      } 
    } 
  }
  
  public Collection<ScoreboardScore> getScores() {
    Collection<Map<ScoreboardObjective, ScoreboardScore>> collection = this.playerScores.values();
    ArrayList<ScoreboardScore> arrayList = Lists.newArrayList();
    for (Map<ScoreboardObjective, ScoreboardScore> map : collection)
      arrayList.addAll(map.values()); 
    return arrayList;
  }
  
  public Map<ScoreboardObjective, ScoreboardScore> getPlayerObjectives(String paramString) {
    Map<ScoreboardObjective, ScoreboardScore> map = this.playerScores.get(paramString);
    if (map == null)
      map = Maps.newHashMap(); 
    return map;
  }
  
  public void unregisterObjective(ScoreboardObjective paramScoreboardObjective) {
    this.objectivesByName.remove(paramScoreboardObjective.getName());
    for (byte b = 0; b < 19; b++) {
      if (getObjectiveForSlot(b) == paramScoreboardObjective)
        setDisplaySlot(b, null); 
    } 
    List list = this.objectivesByCriteria.get(paramScoreboardObjective.getCriteria());
    if (list != null)
      list.remove(paramScoreboardObjective); 
    for (Map<ScoreboardObjective, ScoreboardScore> map : this.playerScores.values())
      map.remove(paramScoreboardObjective); 
    handleObjectiveRemoved(paramScoreboardObjective);
  }
  
  public void setDisplaySlot(int paramInt, ScoreboardObjective paramScoreboardObjective) {
    this.displaySlots[paramInt] = paramScoreboardObjective;
  }
  
  public ScoreboardObjective getObjectiveForSlot(int paramInt) {
    return this.displaySlots[paramInt];
  }
  
  public ScoreboardTeam getTeam(String paramString) {
    return this.teamsByName.get(paramString);
  }
  
  public ScoreboardTeam createTeam(String paramString) {
    if (paramString.length() > 16)
      throw new IllegalArgumentException("The team name '" + paramString + "' is too long!"); 
    ScoreboardTeam scoreboardTeam = getTeam(paramString);
    if (scoreboardTeam != null)
      throw new IllegalArgumentException("A team with the name '" + paramString + "' already exists!"); 
    scoreboardTeam = new ScoreboardTeam(this, paramString);
    this.teamsByName.put(paramString, scoreboardTeam);
    handleTeamAdded(scoreboardTeam);
    return scoreboardTeam;
  }
  
  public void removeTeam(ScoreboardTeam paramScoreboardTeam) {
    this.teamsByName.remove(paramScoreboardTeam.getName());
    for (String str : paramScoreboardTeam.getPlayerNameSet())
      this.teamsByPlayer.remove(str); 
    handleTeamRemoved(paramScoreboardTeam);
  }
  
  public boolean addPlayerToTeam(String paramString1, String paramString2) {
    if (paramString1.length() > 40)
      throw new IllegalArgumentException("The player name '" + paramString1 + "' is too long!"); 
    if (!this.teamsByName.containsKey(paramString2))
      return false; 
    ScoreboardTeam scoreboardTeam = getTeam(paramString2);
    if (getPlayerTeam(paramString1) != null)
      removePlayerFromTeam(paramString1); 
    this.teamsByPlayer.put(paramString1, scoreboardTeam);
    scoreboardTeam.getPlayerNameSet().add(paramString1);
    return true;
  }
  
  public boolean removePlayerFromTeam(String paramString) {
    ScoreboardTeam scoreboardTeam = getPlayerTeam(paramString);
    if (scoreboardTeam != null) {
      removePlayerFromTeam(paramString, scoreboardTeam);
      return true;
    } 
    return false;
  }
  
  public void removePlayerFromTeam(String paramString, ScoreboardTeam paramScoreboardTeam) {
    if (getPlayerTeam(paramString) != paramScoreboardTeam)
      throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team '" + paramScoreboardTeam.getName() + "'."); 
    this.teamsByPlayer.remove(paramString);
    paramScoreboardTeam.getPlayerNameSet().remove(paramString);
  }
  
  public Collection<String> getTeamNames() {
    return this.teamsByName.keySet();
  }
  
  public Collection<ScoreboardTeam> getTeams() {
    return this.teamsByName.values();
  }
  
  public ScoreboardTeam getPlayerTeam(String paramString) {
    return this.teamsByPlayer.get(paramString);
  }
  
  public void handleObjectiveAdded(ScoreboardObjective paramScoreboardObjective) {}
  
  public void handleObjectiveChanged(ScoreboardObjective paramScoreboardObjective) {}
  
  public void handleObjectiveRemoved(ScoreboardObjective paramScoreboardObjective) {}
  
  public void handleScoreChanged(ScoreboardScore paramScoreboardScore) {}
  
  public void handlePlayerRemoved(String paramString) {}
  
  public void a(String paramString, ScoreboardObjective paramScoreboardObjective) {}
  
  public void handleTeamAdded(ScoreboardTeam paramScoreboardTeam) {}
  
  public void handleTeamChanged(ScoreboardTeam paramScoreboardTeam) {}
  
  public void handleTeamRemoved(ScoreboardTeam paramScoreboardTeam) {}
  
  public static String getSlotName(int paramInt) {
    switch (paramInt) {
      case 0:
        return "list";
      case 1:
        return "sidebar";
      case 2:
        return "belowName";
    } 
    if (paramInt >= 3 && paramInt <= 18) {
      EnumChatFormat enumChatFormat = EnumChatFormat.a(paramInt - 3);
      if (enumChatFormat != null && enumChatFormat != EnumChatFormat.RESET)
        return "sidebar.team." + enumChatFormat.e(); 
    } 
    return null;
  }
  
  public static int getSlotForName(String paramString) {
    if (paramString.equalsIgnoreCase("list"))
      return 0; 
    if (paramString.equalsIgnoreCase("sidebar"))
      return 1; 
    if (paramString.equalsIgnoreCase("belowName"))
      return 2; 
    if (paramString.startsWith("sidebar.team.")) {
      String str = paramString.substring("sidebar.team.".length());
      EnumChatFormat enumChatFormat = EnumChatFormat.b(str);
      if (enumChatFormat != null && enumChatFormat.b() >= 0)
        return enumChatFormat.b() + 3; 
    } 
    return -1;
  }
  
  private static String[] g = null;
  
  public static String[] h() {
    if (g == null) {
      g = new String[19];
      for (byte b = 0; b < 19; b++)
        g[b] = getSlotName(b); 
    } 
    return g;
  }
  
  public void a(Entity paramEntity) {
    if (paramEntity == null || paramEntity instanceof EntityHuman || paramEntity.isAlive())
      return; 
    String str = paramEntity.getUniqueID().toString();
    resetPlayerScores(str, null);
    removePlayerFromTeam(str);
  }
}
