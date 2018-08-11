/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.tankbattle.listener;

import com.github.peterchenhdu.future.example.tankbattle.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class Description...
 *
 * @author chenpi
 * @since 2018/3/19 20:18
 */
@Component
public class MenuActionListener implements ActionListener {
    @Autowired
    private CommandService commandService;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JMenuItem) {
            JMenuItem m = (JMenuItem) o;
            System.out.println(m.getText());
            if ("customMap".equals(e.getActionCommand())) {
                commandService.executeCustomMapMenu(m.getText());
            } else {
                commandService.executeByCmd(e.getActionCommand());
            }

        } else {
            System.out.println("do nothing" + e.getActionCommand());
        }


    }
}
