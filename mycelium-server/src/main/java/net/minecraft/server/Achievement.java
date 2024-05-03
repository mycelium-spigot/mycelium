package net.minecraft.server;

public class Achievement extends Statistic {
    public final int a;

    public final int b;

    public final Achievement c;

    private final String k;

    public final ItemStack d;

    private boolean m;

    public Achievement(String paramString1, String paramString2, int paramInt1, int paramInt2, Item paramItem, Achievement paramAchievement) {
        this(paramString1, paramString2, paramInt1, paramInt2, new ItemStack(paramItem), paramAchievement);
    }

    public Achievement(String paramString1, String paramString2, int paramInt1, int paramInt2, Block paramBlock, Achievement paramAchievement) {
        this(paramString1, paramString2, paramInt1, paramInt2, new ItemStack(paramBlock), paramAchievement);
    }

    public Achievement(String paramString1, String paramString2, int paramInt1, int paramInt2, ItemStack paramItemStack, Achievement paramAchievement) {
        super(paramString1, new ChatMessage("achievement." + paramString2, new Object[0]));
        this.d = paramItemStack;
        this.k = "achievement." + paramString2 + ".desc";
        this.a = paramInt1;
        this.b = paramInt2;
        if (paramInt1 < AchievementList.a)
            AchievementList.a = paramInt1;
        if (paramInt2 < AchievementList.b)
            AchievementList.b = paramInt2;
        if (paramInt1 > AchievementList.c)
            AchievementList.c = paramInt1;
        if (paramInt2 > AchievementList.d)
            AchievementList.d = paramInt2;
        this.c = paramAchievement;
    }

    public Achievement a() {
        this.f = true;
        return this;
    }

    public Achievement b() {
        this.m = true;
        return this;
    }

    public Achievement c() {
        super.h();
        AchievementList.e.add(this);
        return this;
    }

    public boolean d() {
        return true;
    }

    public IChatBaseComponent e() {
        IChatBaseComponent iChatBaseComponent = super.e();
        iChatBaseComponent.getChatModifier().setColor(g() ? EnumChatFormat.DARK_PURPLE : EnumChatFormat.GREEN);
        return iChatBaseComponent;
    }

    public Achievement a(Class<? extends IJsonStatistic> paramClass) {
        return (Achievement) super.b(paramClass);
    }

    public boolean g() {
        return this.m;
    }
}