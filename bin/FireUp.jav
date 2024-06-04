public class FireUp implements Item {
    private boolean isActive;

    @Override
    public void activate() {
        isActive = true;
        // 在這裡添加火力加倍的相關邏輯
    }
    
    @Override
    public void deactivate() {
        isActive = false;
        // 在這裡添加火力加倍結束後的相關邏輯
    }
}