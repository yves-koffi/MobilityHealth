<?php

Schema::create('users', function (Blueprint $table) {
            $table->id();
            $table->string('first_name');
            $table->string('last_name');
            $table->date('date_of_birth');
            $table->string('nationality');
            $table->string('country_of_residence');
            $table->string('phone_number')->unique();
            $table->string('whatsapp_number')->nullable();
            $table->string('email')->unique();
            $table->string('password');
            $table->string('profile_picture')->nullable();
            $table->string('id_document')->nullable();
            $table->enum('role', ['insured', 'subscriber', 'referral_doctor', 'admin']);
            $table->timestamps();
        });

          Schema::create('subscriptions', function (Blueprint $table) {
                    $table->id();
                    $table->foreignId('user_id')->constrained()->onDelete('cascade');
                    $table->dateTime('subscription_date');
                    $table->date('start_date');
                    $table->date('end_date');
                    $table->string('destination');
                    $table->enum('plan', ['Basic', 'Premium']);
                    $table->enum('status', ['pending', 'approved', 'rejected']);
                    $table->timestamps();
                });

                    Schema::create('insurance_policies', function (Blueprint $table) {
                            $table->id();
                            $table->foreignId('subscription_id')->constrained()->onDelete('cascade');
                            $table->string('policy_number')->unique();
                            $table->date('validation_date');
                            $table->enum('status', ['active', 'inactive']);
                            $table->timestamps();
                        });

                           Schema::create('medical_questionnaires', function (Blueprint $table) {
                                    $table->id();
                                    $table->foreignId('user_id')->constrained()->onDelete('cascade');
                                    $table->json('answers');
                                    $table->dateTime('submission_date');
                                    $table->enum('status', ['complete', 'incomplete']);
                                    $table->timestamps();
                                });

                                 Schema::create('payments', function (Blueprint $table) {
                                            $table->id();
                                            $table->foreignId('subscription_id')->constrained()->onDelete('cascade');
                                            $table->decimal('amount', 10, 2);
                                            $table->dateTime('payment_date');
                                            $table->enum('payment_method', ['credit_card', 'mobile_money']);
                                            $table->enum('status', ['success', 'failed']);
                                            $table->timestamps();
                                        });

                                        Schema::create('referral_doctors', function (Blueprint $table) {
                                                    $table->id();
                                                    $table->string('first_name');
                                                    $table->string('last_name');
                                                    $table->string('specialty');
                                                    $table->string('phone_number');
                                                    $table->string('email');
                                                    $table->point('location');
                                                    $table->timestamps();
                                                });

                                                Schema::create('health_partners', function (Blueprint $table) {
                                                            $table->id();
                                                            $table->string('name');
                                                            $table->string('address');
                                                            $table->string('phone_number');
                                                            $table->string('email');
                                                            $table->point('location');
                                                            $table->timestamps();
                                                        });

                                                        Schema::create('claims', function (Blueprint $table) {
                                                                    $table->id();
                                                                    $table->foreignId('user_id')->constrained()->onDelete('cascade');
                                                                    $table->dateTime('claim_date');
                                                                    $table->point('location');
                                                                    $table->text('description');
                                                                    $table->enum('status', ['pending', 'processed', 'closed']);
                                                                    $table->timestamps();
                                                                });

                                                                  Schema::create('invoices', function (Blueprint $table) {
                                                                            $table->id();
                                                                            $table->foreignId('claim_id')->constrained()->onDelete('cascade');
                                                                            $table->foreignId('health_partner_id')->constrained()->onDelete('cascade');
                                                                            $table->decimal('amount', 10, 2);
                                                                            $table->date('invoice_date');
                                                                            $table->enum('status', ['pending', 'paid']);
                                                                            $table->timestamps();
                                                                        });

                                                                         Schema::create('insurance_cards', function (Blueprint $table) {
                                                                                    $table->id();
                                                                                    $table->foreignId('user_id')->constrained()->onDelete('cascade');
                                                                                    $table->string('card_number')->unique();
                                                                                    $table->date('issue_date');
                                                                                    $table->date('expiry_date');
                                                                                    $table->enum('status', ['active', 'inactive']);
                                                                                    $table->timestamps();
                                                                                });