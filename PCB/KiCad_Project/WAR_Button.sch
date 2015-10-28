EESchema Schematic File Version 2
LIBS:power
LIBS:device
LIBS:transistors
LIBS:conn
LIBS:linear
LIBS:regul
LIBS:74xx
LIBS:cmos4000
LIBS:adc-dac
LIBS:memory
LIBS:xilinx
LIBS:microcontrollers
LIBS:dsp
LIBS:microchip
LIBS:analog_switches
LIBS:motorola
LIBS:texas
LIBS:intel
LIBS:audio
LIBS:interface
LIBS:digital-audio
LIBS:philips
LIBS:display
LIBS:cypress
LIBS:siliconi
LIBS:opto
LIBS:atmel
LIBS:contrib
LIBS:valves
LIBS:bc118
LIBS:attiny
LIBS:WAR_Button-cache
EELAYER 25 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L ATTINY84A-MM IC1
U 1 1 560D4AAC
P 2100 3900
F 0 "IC1" H 1250 4650 40  0000 C CNN
F 1 "ATTINY84A-MM" H 2750 3150 40  0000 C CNN
F 2 "VQFN20" H 2100 3700 35  0000 C CIN
F 3 "" H 2100 3900 60  0000 C CNN
	1    2100 3900
	1    0    0    -1  
$EndComp
$Comp
L BC118 BLE1
U 1 1 560D65FC
P 9900 4200
F 0 "BLE1" H 9650 5300 60  0000 C CNN
F 1 "BC118" H 9650 5400 60  0000 C CNN
F 2 "" H 9650 5400 60  0001 C CNN
F 3 "" H 9650 5400 60  0000 C CNN
	1    9900 4200
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR01
U 1 1 560D6740
P 8350 3050
F 0 "#PWR01" H 8350 2800 50  0001 C CNN
F 1 "GND" H 8350 2900 50  0000 C CNN
F 2 "" H 8350 3050 60  0000 C CNN
F 3 "" H 8350 3050 60  0000 C CNN
	1    8350 3050
	0    1    1    0   
$EndComp
$Comp
L +3V3 #PWR02
U 1 1 560D743F
P 800 3300
F 0 "#PWR02" H 800 3150 50  0001 C CNN
F 1 "+3V3" H 800 3440 50  0000 C CNN
F 2 "" H 800 3300 60  0000 C CNN
F 3 "" H 800 3300 60  0000 C CNN
	1    800  3300
	0    -1   -1   0   
$EndComp
$Comp
L GND #PWR03
U 1 1 560D758B
P 800 4500
F 0 "#PWR03" H 800 4250 50  0001 C CNN
F 1 "GND" H 800 4350 50  0000 C CNN
F 2 "" H 800 4500 60  0000 C CNN
F 3 "" H 800 4500 60  0000 C CNN
	1    800  4500
	0    1    1    0   
$EndComp
$Comp
L +3V3 #PWR04
U 1 1 560D7738
P 8500 4850
F 0 "#PWR04" H 8500 4700 50  0001 C CNN
F 1 "+3V3" H 8500 4990 50  0000 C CNN
F 2 "" H 8500 4850 60  0000 C CNN
F 3 "" H 8500 4850 60  0000 C CNN
	1    8500 4850
	0    -1   -1   0   
$EndComp
$Comp
L C_Small C1
U 1 1 560D7779
P 8550 4550
F 0 "C1" H 8560 4620 50  0000 L CNN
F 1 "10nF" H 8560 4470 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 8550 4550 60  0001 C CNN
F 3 "" H 8550 4550 60  0000 C CNN
	1    8550 4550
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR05
U 1 1 560D7FCC
P 4350 4200
F 0 "#PWR05" H 4350 3950 50  0001 C CNN
F 1 "GND" H 4350 4050 50  0000 C CNN
F 2 "" H 4350 4200 60  0000 C CNN
F 3 "" H 4350 4200 60  0000 C CNN
	1    4350 4200
	0    -1   -1   0   
$EndComp
$Comp
L SW_PUSH SW1
U 1 1 560D7FE8
P 3900 4200
F 0 "SW1" H 4050 4310 50  0000 C CNN
F 1 "SW_PUSH" H 3900 4120 50  0000 C CNN
F 2 "" H 3900 4200 60  0001 C CNN
F 3 "" H 3900 4200 60  0000 C CNN
	1    3900 4200
	1    0    0    -1  
$EndComp
$Comp
L Battery BT1
U 1 1 560D9989
P 6350 3900
F 0 "BT1" H 6450 3950 50  0000 L CNN
F 1 "Battery" H 6450 3850 50  0000 L CNN
F 2 "" V 6350 3940 60  0001 C CNN
F 3 "" V 6350 3940 60  0000 C CNN
	1    6350 3900
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR06
U 1 1 560D9BA1
P 6350 4250
F 0 "#PWR06" H 6350 4000 50  0001 C CNN
F 1 "GND" H 6350 4100 50  0000 C CNN
F 2 "" H 6350 4250 60  0000 C CNN
F 3 "" H 6350 4250 60  0000 C CNN
	1    6350 4250
	1    0    0    -1  
$EndComp
$Comp
L +3V3 #PWR07
U 1 1 560D9BC3
P 6350 3550
F 0 "#PWR07" H 6350 3400 50  0001 C CNN
F 1 "+3V3" H 6350 3690 50  0000 C CNN
F 2 "" H 6350 3550 60  0000 C CNN
F 3 "" H 6350 3550 60  0000 C CNN
	1    6350 3550
	1    0    0    -1  
$EndComp
$Comp
L +3V3 #PWR08
U 1 1 560DA4CD
P 3700 3300
F 0 "#PWR08" H 3700 3150 50  0001 C CNN
F 1 "+3V3" H 3700 3440 50  0000 C CNN
F 2 "" H 3700 3300 60  0000 C CNN
F 3 "" H 3700 3300 60  0000 C CNN
	1    3700 3300
	0    1    1    0   
$EndComp
Wire Wire Line
	8350 3050 8850 3050
Wire Wire Line
	8850 3050 8850 4350
Connection ~ 8850 3150
Connection ~ 8850 3250
Connection ~ 8850 3350
Connection ~ 8850 3450
Connection ~ 8850 3550
Connection ~ 8850 3650
Connection ~ 8850 3750
Connection ~ 8850 3850
Connection ~ 8850 3950
Connection ~ 8850 4050
Connection ~ 8850 4150
Connection ~ 8850 4250
Wire Wire Line
	800  3300 1050 3300
Wire Wire Line
	800  4500 1050 4500
Wire Wire Line
	8500 4850 8850 4850
Wire Wire Line
	8850 4850 8850 4750
Wire Wire Line
	8550 4850 8550 4650
Connection ~ 8550 4850
Wire Wire Line
	8550 4450 8550 4350
Wire Wire Line
	8550 4350 8850 4350
Wire Wire Line
	4200 4200 4350 4200
Wire Wire Line
	3600 4200 3150 4200
Text GLabel 10750 3350 2    60   Input ~ 0
BLE_TX
Text GLabel 10750 3450 2    60   Input ~ 0
BLE_RX
Wire Wire Line
	10550 3350 10750 3350
Wire Wire Line
	10550 3450 10750 3450
Text GLabel 4100 3400 2    60   Input ~ 0
BLE_RX
Text GLabel 4100 3550 2    60   Input ~ 0
BLE_TX
Wire Wire Line
	3150 3400 4100 3400
Wire Wire Line
	3150 3500 4100 3500
Wire Wire Line
	4100 3500 4100 3550
Wire Wire Line
	3150 3300 3700 3300
Wire Wire Line
	3550 3600 3150 3600
Text GLabel 3550 3600 2    60   Input ~ 0
WAKE
Text GLabel 10750 4850 2    60   Input ~ 0
WAKE
Text GLabel 2850 1950 3    60   Input ~ 0
P1
Text GLabel 2950 1950 3    60   Input ~ 0
P2
Text GLabel 3050 1950 3    60   Input ~ 0
P3
Text GLabel 3150 1950 3    60   Input ~ 0
P4
$Comp
L CONN_01X06 P1
U 1 1 561F3005
P 2900 1750
F 0 "P1" H 2900 2100 50  0000 C CNN
F 1 "CONN_01X06" V 3000 1750 50  0000 C CNN
F 2 "" H 2900 1750 60  0001 C CNN
F 3 "" H 2900 1750 60  0000 C CNN
	1    2900 1750
	0    -1   -1   0   
$EndComp
Text GLabel 2600 2250 3    60   Input ~ 0
PROG_VIN
Text GLabel 2750 2250 3    60   Input ~ 0
PROG_GND
Wire Wire Line
	2750 1950 2750 2250
Wire Wire Line
	2600 2250 2600 1950
Wire Wire Line
	2600 1950 2650 1950
Text GLabel 6100 4100 3    60   Input ~ 0
PROG_GND
Wire Wire Line
	6100 4100 6100 4050
Wire Wire Line
	6350 4050 6350 4250
Wire Wire Line
	6350 3750 6350 3550
Text GLabel 3150 3700 2    60   Input ~ 0
P1
Text GLabel 3150 3800 2    60   Input ~ 0
P2
Text GLabel 3150 3900 2    60   Input ~ 0
P3
Text GLabel 3150 4500 2    60   Input ~ 0
P4
$Comp
L LED D1
U 1 1 561F3B46
P 4100 3850
F 0 "D1" H 4100 3950 50  0000 C CNN
F 1 "LED" H 4100 3750 50  0000 C CNN
F 2 "LEDs:LED-0603" H 4100 3850 60  0001 C CNN
F 3 "" H 4100 3850 60  0000 C CNN
	1    4100 3850
	-1   0    0    1   
$EndComp
$Comp
L R_Small R1
U 1 1 561F3B97
P 3650 3850
F 0 "R1" H 3680 3870 50  0000 L CNN
F 1 "47" H 3680 3810 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 3650 3850 60  0001 C CNN
F 3 "" H 3650 3850 60  0000 C CNN
	1    3650 3850
	0    1    1    0   
$EndComp
Wire Wire Line
	3150 4000 3550 4000
Wire Wire Line
	3550 4000 3550 3850
Wire Wire Line
	3750 3850 3900 3850
$Comp
L GND #PWR09
U 1 1 561F3C70
P 4400 3850
F 0 "#PWR09" H 4400 3600 50  0001 C CNN
F 1 "GND" H 4400 3700 50  0000 C CNN
F 2 "" H 4400 3850 60  0000 C CNN
F 3 "" H 4400 3850 60  0000 C CNN
	1    4400 3850
	0    -1   -1   0   
$EndComp
Wire Wire Line
	4300 3850 4400 3850
Wire Wire Line
	6100 4050 6350 4050
Wire Wire Line
	6100 3650 6100 3750
Text GLabel 6100 3650 1    60   Input ~ 0
PROG_VIN
Wire Wire Line
	6100 3750 6350 3750
Wire Wire Line
	10550 4850 10750 4850
$Comp
L R_Small R2
U 1 1 56303048
P 10650 5050
F 0 "R2" H 10680 5070 50  0000 L CNN
F 1 "100K" H 10680 5010 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 10650 5050 60  0001 C CNN
F 3 "" H 10650 5050 60  0000 C CNN
	1    10650 5050
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR010
U 1 1 563030D2
P 10650 5250
F 0 "#PWR010" H 10650 5000 50  0001 C CNN
F 1 "GND" H 10650 5100 50  0000 C CNN
F 2 "" H 10650 5250 60  0000 C CNN
F 3 "" H 10650 5250 60  0000 C CNN
	1    10650 5250
	1    0    0    -1  
$EndComp
Wire Wire Line
	10650 4950 10650 4850
Connection ~ 10650 4850
Wire Wire Line
	10650 5150 10650 5250
$Comp
L GND #PWR011
U 1 1 56304562
P 5200 4300
F 0 "#PWR011" H 5200 4050 50  0001 C CNN
F 1 "GND" H 5200 4150 50  0000 C CNN
F 2 "" H 5200 4300 60  0000 C CNN
F 3 "" H 5200 4300 60  0000 C CNN
	1    5200 4300
	0    -1   -1   0   
$EndComp
$Comp
L SW_PUSH SW2
U 1 1 56304568
P 4750 4300
F 0 "SW2" H 4900 4410 50  0000 C CNN
F 1 "SW_PUSH" H 4750 4220 50  0000 C CNN
F 2 "" H 4750 4300 60  0001 C CNN
F 3 "" H 4750 4300 60  0000 C CNN
	1    4750 4300
	1    0    0    -1  
$EndComp
Wire Wire Line
	5050 4300 5200 4300
Wire Wire Line
	4450 4300 4150 4300
Wire Wire Line
	4150 4300 4150 4350
Wire Wire Line
	4150 4350 3700 4350
Wire Wire Line
	3700 4350 3700 4300
Wire Wire Line
	3700 4300 3150 4300
$EndSCHEMATC
