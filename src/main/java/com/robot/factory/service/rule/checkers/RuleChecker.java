package com.robot.factory.service.rule.checkers;

public abstract class RuleChecker {
    private RuleChecker next;

    public RuleChecker linkWith(RuleChecker next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(Character[] components);

    protected boolean checkNext(Character[] components) {
        if (next == null) {
            return true;
        }
        return next.check(components);
    }
}
