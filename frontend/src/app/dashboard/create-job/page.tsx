'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { User } from '@/types';
import CreateJobForm from '@/components/jobs/CreateJobForm';
import { ArrowLeft } from 'lucide-react';
import Button from '@/components/ui/Button';

export default function CreateJobPage() {
  const [user, setUser] = useState<User | null>(null);
  const router = useRouter();

  useEffect(() => {
    const savedUser = localStorage.getItem('user');
    if (savedUser) {
      const userData = JSON.parse(savedUser);
      setUser(userData);
      
      // Redirect if user is not an employer
      if (userData.userType !== 'employer') {
        router.push('/dashboard');
      }
    }
  }, [router]);

  const handleSuccess = () => {
    router.push('/dashboard/jobs');
  };

  const handleCancel = () => {
    router.push('/dashboard');
  };

  if (!user || user.userType !== 'employer') {
    return (
      <div className="text-center py-12">
        <p className="text-gray-600">Access denied. Only employers can create job posts.</p>
        <Button
          onClick={() => router.push('/dashboard')}
          className="mt-4"
        >
          Back to Dashboard
        </Button>
      </div>
    );
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center space-x-4">
        <Button
          onClick={() => router.push('/dashboard')}
          variant="outline"
          size="sm"
        >
          <ArrowLeft className="w-4 h-4 mr-2" />
          Back
        </Button>
        <div>
          <h1 className="text-3xl font-bold text-gray-900">Create Job Post</h1>
          <p className="text-gray-600 mt-2">
            Post a new job opportunity and find the perfect candidate
          </p>
        </div>
      </div>

      {/* Form */}
      <CreateJobForm
        onSuccess={handleSuccess}
        onCancel={handleCancel}
      />
    </div>
  );
}



